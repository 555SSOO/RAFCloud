import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Product} from '../models/product.model';
import {SpringUrls} from './spring-urls';
import {UserService} from './user.service';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private productUrl: string;
  private videoUrl: string;

  constructor(private http: HttpClient, private userService: UserService) {
    this.productUrl = SpringUrls.CHILLFLIX_ENDPOINT + '/product';
    this.videoUrl = SpringUrls.VIDEOSERVICE_ENDPOINT + '/video';
  }
  public getProductsForUser(userId: string): Observable<Product[]> {
    const params = new HttpParams().set('userId', userId);
    return this.http.get<Product[]>(this.productUrl + '/get-products-for-user', {params});
  }

  public getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.productUrl + '/get-products');
  }

  public updateTimeWatched(timeStamp: number, productId: string) {
    const params = new HttpParams().set('timeStamp', timeStamp.toString()).set('productId', productId).set('userId',
      this.userService.getCurrentUserId());
    return this.http.post(this.productUrl + '/update-time-watched', params);
  }

  public getTimeWatched(productId: string): Observable<number> {
    const params = new HttpParams().set('productId', productId).set('userId', this.userService.getCurrentUserId());
    return this.http.get<number>(this.productUrl + '/get-time-watched', {params});
  }

  public getVideoUrl(productId: string) {
    return this.videoUrl + '/getVideo/' + productId;
  }
}
