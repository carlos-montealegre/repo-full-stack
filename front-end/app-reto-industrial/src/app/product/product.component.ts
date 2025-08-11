import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductFormComponent } from './product-form/product-form.component';
import { ProductListComponent } from './product-list/product-list.component';
import { ProductsService } from './../core/services/product.service';
import { Product } from './../shared/models/product.model';
import { AuthService } from './../core/services/auth.service';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [CommonModule, ProductFormComponent, ProductListComponent],
  template: `
    <div class="container mt-4">
      <div class="d-flex justify-content-between mb-3">
        <h2>Gestión de Productos</h2>
        <button class="btn btn-danger" (click)="logout()">Logout</button>
      </div>

      <app-product-form
        [product]="editingProduct"
        (save)="handleSave($event)"
        (cancel)="cancelEdit()">
      </app-product-form>

      <app-product-list
        [products]="products"
        (edit)="editProduct($event)"
        (delete)="deleteProduct($event)">
      </app-product-list>
    </div>
  `
})
export class ProductsComponent implements OnInit {
  products: Product[] = [];

  editingProduct: Product | null = null;

  constructor(private productsService: ProductsService, private authService: AuthService, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  // loadProducts() {
  //   this.productsService.getAll().subscribe(data => this.products = data);
  //   console.log(this.products);
  // }

  loadProducts() {
  this.productsService.getAll().subscribe({
    next: (data) => {
      console.log('Datos recibidos:', JSON.stringify(data)); // Verifica estructura
      this.products = data;
      // Forzar detección de cambios si es necesario
      this.cdr.detectChanges();
    },
    error: (err) => console.error('Error:', err)
  });
}

  handleSave(product: Product) {
    if (this.editingProduct) {
      this.productsService.update(this.editingProduct.id!, product).subscribe(() => {
        this.loadProducts();
        this.editingProduct = null;
      });
    } else {
      this.productsService.create(product).subscribe(() => this.loadProducts());
    }
  }

  editProduct(product: Product) {
    this.editingProduct = product;
  }

  cancelEdit() {
    this.editingProduct = null;
  }

  deleteProduct(id: number) {
    if (confirm('¿Seguro que deseas eliminar este producto?')) {
      this.productsService.delete(id).subscribe(() => this.loadProducts());
    }
  }

  logout() {
    this.authService.logout();
  }
}
