import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ECommerceComponent } from './e-commerce.component';
import { HomeComponent } from './home/home.component';
import { ConsultsComponent } from './consults/consults.component';
import { StockComponent } from './stock/stock.component';
import { SaleComponent } from './sale/sale.component';

const routes: Routes = [
  {
    path: '',
    component: ECommerceComponent,
    children: [
      {
        path: 'home',
        component: HomeComponent,
      },
      {
        path: 'consults',
        component: ConsultsComponent,
      },
      // {
      //   path: 'customers',
      //   component: CustomersComponent,
      // },
      {
        path: 'stock',
        component: StockComponent,
      },
      {
        path: 'sale',
        component: SaleComponent,
      },
      { path: '', redirectTo: 'customers', pathMatch: 'full' },
      { path: '**', redirectTo: 'customers', pathMatch: 'full' },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ECommerceRoutingModule {}
