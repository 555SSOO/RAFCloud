import { Component, OnInit } from '@angular/core';
import {ProductService} from '../../../core/services/product.service';
import {Product} from '../../../core/models/product.model';
import {Router} from '@angular/router';

@Component({
  selector: 'app-product-listing',
  templateUrl: './product-listing.component.html',
  styleUrls: ['./product-listing.component.css']
})
export class ProductListingComponent implements OnInit {

  userProducts: Product[];
  allProducts: Product[];

  constructor(private productService: ProductService, private router: Router) { }

  ngOnInit() {
    this.productService.getProductsForUser(sessionStorage.getItem('userId')).subscribe(data => {
      this.userProducts = data;
    });
    this.productService.getProducts().subscribe(data => {
      this.allProducts = data;
    });
  }

  goToProductDetailsPage(product: Product) {

    this.router.navigate(['product-details'], {state: {data: product}});


  }

}
