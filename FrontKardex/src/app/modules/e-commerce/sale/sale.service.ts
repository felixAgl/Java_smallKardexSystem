import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ISale } from '../_models/sale.model';

@Injectable({
  providedIn: 'root'
})
export class SaleService {
  constructor(private http: HttpClient) { }

  public addSale(sale: ISale): Observable<any> {
    console.log(sale);
    return this.http.post('/sale/', sale);
  }
}
