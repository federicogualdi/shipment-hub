import { OrderDto } from './dto';
import { OrderFilter } from './dto/OrderFilter';

const ORDERS_ENDPOINT = 'http://localhost:8080/orders';

export const getOrders = async (filters?: OrderFilter): Promise<OrderDto[]> => {
  const qp = '?';
  const params: string[] = [];

  if (filters) {
    for (const [key, value] of Object.entries(filters)) {
      if (value != undefined && !!value) {
        params.push(`${key}=${value}`);
      }
    }
  }
  return fetch(`${ORDERS_ENDPOINT}${params.length > 0 ? qp + params.join('&') : ''}`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json'
    }
  }).then(async (res) => await res.json());
};
