package rs.raf.cloud.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import rs.raf.cloud.entities.Machine;
import rs.raf.cloud.entities.MachineQueryModel;
import rs.raf.cloud.repository.MachineRepository;
import rs.raf.cloud.repository.MachineSearchRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Repository
public class RafMachineSearchRepository implements MachineSearchRepository {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    public List<Machine> searchMachines(MachineQueryModel machineQueryModel){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Machine> query = criteriaBuilder.createQuery(Machine.class);

        Root<Machine> root = query.from(Machine.class);
        List<Predicate> predicates = new ArrayList<>();

        if(!StringUtils.isEmpty(machineQueryModel.getMachineName())){
            predicates.add(criteriaBuilder.equal(root.get("name"), machineQueryModel.getMachineName()));
        }
        if(machineQueryModel.getStatus() != null){
            predicates.add(criteriaBuilder.equal(root.get("status"), machineQueryModel.getStatus()));
        }
        if(machineQueryModel.getDateFrom() != null && machineQueryModel.getDateTo() != null){
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.<Date>get("creationDate"), machineQueryModel.getDateTo()));
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.<Date>get("creationDate"), machineQueryModel.getDateFrom()));
        }

        query.select(root).where(predicates.toArray(new Predicate[]{}));
        return entityManager.createQuery(query).getResultList();
    }
}
