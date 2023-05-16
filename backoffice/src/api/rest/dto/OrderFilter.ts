export interface OrderFilter {
  name?: string;
  orderBy?: string;
  orderDirection?: 'asc' | 'desc';
  skip?: number;
  top?: number;
}
