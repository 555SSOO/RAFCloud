package rs.raf.chillflix.videoservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("videoservice")
public interface VideoServiceProxy {

    @RequestMapping("/video/getVideo/{id}")
    ResponseEntity<Resource> getVideo(@PathVariable String id);
}
