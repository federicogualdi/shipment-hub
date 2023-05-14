import { Package } from './PackageDto';

export interface Order {
  id: number;
  packages: Package[];
}
