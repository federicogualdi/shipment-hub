import { DepotDto } from './DepotDto';
import { PackageDto } from './PackageDto';
import { SupplierDto } from './SupplierDto';

export interface OrderDto {
  id: number;
  depot: DepotDto;
  supplier: SupplierDto;
  packages: PackageDto[];
}
