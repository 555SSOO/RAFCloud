import {Component, OnInit} from '@angular/core';
import {Product} from '../../../core/models/product.model';
import {ProductService} from '../../../core/services/product.service';
import {VgAPI} from 'videogular2/compiled/src/core/services/vg-api';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  product: Product;
  api: VgAPI;
  currentTime = 0;

  constructor(private productService: ProductService) { }

  ngOnInit() {
    if (!this.product) {
      this.product = history.state.data;
    }
  }

  getVideoUrl(): string {
    return this.productService.getVideoUrl(this.product.videoIds[0].toString());
  }

  onPlayerReady(api: VgAPI) {
    this.api = api;
    this.productService.getTimeWatched(this.product.id.toString()).subscribe(timeWatched => {
      this.api.seekTime(timeWatched, false);
    });

    this.api.subscriptions.timeUpdate.subscribe(data => {
      // Only send the timestamp every second - to not over-flood the server
      const timeWatched = Math.round(data.srcElement.currentTime);
      if (timeWatched > 0 && timeWatched > this.currentTime) {
        this.currentTime = timeWatched;
        this.productService.updateTimeWatched(timeWatched, this.product.id.toString()).subscribe();
      }
    });
  }

}
