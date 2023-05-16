import { PackageStatus } from './enums';

export interface PackageDto {
  id: number;
  latitude: number;
  longitude: number;
  status: PackageStatus;
}
