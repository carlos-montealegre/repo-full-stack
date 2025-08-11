import { Component, EventEmitter, Input, Output, OnChanges, SimpleChanges } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Product } from '../../shared/models/product.model';

@Component({
  selector: 'app-product-form',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './product-form.component.html'
})
export class ProductFormComponent implements OnChanges{
  @Input() product: Product | null = null;
  @Output() save = new EventEmitter<Product>();
  @Output() cancel = new EventEmitter<void>();

  form: FormGroup;

  constructor(private fb: FormBuilder) {
    this.form = this.fb.group({
      name: [''],
      price: [0],
      stock: [0],
      createdByUserId: [1] // luego lo llenaremos con el ID real del usuario
    });
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['product'] && this.product) {
      this.form.patchValue(this.product);
    }
  }

  onSubmit() {
    this.save.emit(this.form.value);
    this.form.reset({ name: '', price: 0, stock: 0, createdByUserId: 1 });
  }

  onCancel() {
    this.cancel.emit();
    this.form.reset({ name: '', price: 0, stock: 0, createdByUserId: 1 });
  }
 }
