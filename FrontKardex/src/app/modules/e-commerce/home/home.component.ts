import { Component } from '@angular/core';
import { StockService } from '../stock/stock.service';
import { IProduct } from '../_models/product.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {

  id: number;
  name: string;
  quantity: number;
  textMessage: string;
  products: IProduct[];

  constructor(private stockService: StockService) {
    this.listProducts();
  }

  listProducts() {
    this.textMessage = '';
    this.stockService.listProducts().subscribe((products) => {
      this.products = products;
    },
      (error) => { this.textMessage = error.error.message;
      });
  }
}
