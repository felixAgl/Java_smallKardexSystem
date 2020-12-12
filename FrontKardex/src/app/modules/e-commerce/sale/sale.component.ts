import { Component, OnInit } from '@angular/core';
import { StockService } from '../stock/stock.service';
import { IProduct, Product } from '../_models/product.model';
import { Sale } from '../_models/sale.model';
import { SaleService } from './sale.service';

@Component({
  selector: 'app-sale',
  templateUrl: './sale.component.html',
  styleUrls: ['./sale.component.scss']
})
export class SaleComponent implements OnInit {

  id: number;
  refProduct: number;
  name: string;
  detail: string;
  quantity: number;
  cost: number;
  total: number;
  message: string;
  product: IProduct = new Product();
  stockMessage: string;
  suggestedPriceMessage: string;

  constructor(private saleService: SaleService, private stockService: StockService) { }
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  addSale() {
    this.message = '';
    const sale = new Sale(this.id, this.refProduct, this.name, this.detail, this.quantity, this.cost, this.total);
    this.saleService.addSale(sale).subscribe(() => {
      this.message = 'Registered sale';
    },
      (error) => { this.message = error.error.message; });
  }

  calculateTotal() {
    this.total = this.cost * this.quantity;
  }

  search() {
    console.log(this.refProduct);
    this.stockService.getProduct(this.refProduct).subscribe((product) => {
      this.product = product;
      console.log(product);
      this.name = product.name;
      this.stockMessage = 'Stock: ' + product.quantity;
      this.suggestedPriceMessage = 'Suggested price: ' + product.cost;
    },
      (error) => { this.message = error.error.message; });
  }
}
