import { Component, EventEmitter, Input, Output, ChangeDetectionStrategy, SimpleChanges  } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Product } from '../../shared/models/product.model';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './product-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ProductListComponent {
  @Input() products: Product[] = [];
  @Output() edit = new EventEmitter<Product>();
  @Output() delete = new EventEmitter<number>();

  // En product-list.component.ts
  ngOnChanges(changes: SimpleChanges) {
    if (changes['products']) {
    console.log('Productos recibidos en hijo:', changes['products'].currentValue);
  }
  }

  onEdit(product: Product) {
    this.edit.emit(product);
  }

  onDelete(id: number) {
    this.delete.emit(id);
  }
}
