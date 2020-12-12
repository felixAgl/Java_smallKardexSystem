import { Component, OnInit } from '@angular/core';
import { StockService } from '../stock/stock.service';
import { IProduct, Product } from '../_models/product.model';

@Component({
  selector: 'app-consults',
  templateUrl: './consults.component.html',
  styleUrls: ['./consults.component.scss']
})
export class ConsultsComponent implements OnInit {

  id: number;
  name: string;
  quantity: number;
  product: IProduct = new Product();
  message: string;

  constructor(private stockService: StockService) { }
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  getProduct() {
    this.message = '';
    this.stockService.getProduct(this.id).subscribe((product) => {
      this.product = product;
    },
      (error) => { this.message = error.error.message; });
  }

  updateProduct() {
    this.message = '';
    this.stockService.getProduct(this.id).subscribe((product) => {
      this.product = product;
      this.product.name = this.name;
      this.stockService.updateProduct(this.id, this.product).subscribe(() => {
        this.stockService.listProducts();
        this.getProduct();
        this.message = 'Updated name';
      },
        (error) => { this.message = error.error.message; });
    },
      (error) => { this.message = error.error.message; });
  }

  deleteProduct() {
    this.message = '';
    console.log(this.id);
    this.stockService.getProduct(this.id).subscribe((product) => {
      this.product = product;
      console.log('Cantidad stock');
      console.log(this.product.quantity);
      if (this.product.quantity === 0) {
        this.stockService.deleteProduct(this.id).subscribe(() => {
          this.message = 'Item removed';
        },
          (error) => { this.message = error.error.message; });
      }
      this.message = 'Cannot be removed. There are units in stock.';
    },
      (error) => { this.message = error.error.message; });
  }

}
