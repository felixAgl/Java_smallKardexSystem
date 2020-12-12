import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IProduct, Product } from '../_models/product.model';

@Injectable({
  providedIn: 'root'
})
export class StockService {
  constructor(private http: HttpClient) { }

  public addProduct(stock: IProduct): Observable<any> {
    console.log(stock);
    return this.http.post('/products/', stock);
  }

  public updateProduct(id: number, stock: IProduct): Observable<any> {
    return this.http.put('/products/' + id, stock);
  }

  public deleteProduct(id: number): Observable<any> {
    return this.http.delete('/products/' + id);
  }

  public getProduct(id: number): Observable<IProduct> {
    return this.http.get<Product>('/products/' + id);
  }

  public listProducts(): Observable<IProduct[]> {
    return this.http.get<Product[]>('/products/');
  }
}
