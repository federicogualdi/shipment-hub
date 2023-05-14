import React, { useEffect, useState } from 'react';
import { createUseStyles } from 'react-jss';
import OrderList from './OrderList';
import { Order, getOrders } from '../api';
import { OrderFilter } from '../api/rest/dto/OrderFilter';

const orderStyles = createUseStyles({
  'orders-container': {
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'center',
    alignItems: 'center',
    background: 'rgb(32, 35, 41)',
    minHeight: '60vh'
  }
});
const Orders = () => {
  const [orders, setOrders] = useState<Order[]>([]);

  const [filterName, setFilterName] = useState<string>();

  useEffect(() => {
    const orderApi = async (filters?: OrderFilter) =>
      getOrders(filters).then((res) => {
        setOrders(res ?? []);
      });

    orderApi({ name: filterName });
  }, [filterName]);

  const styles = orderStyles();
  return <>{OrderList(orders)}</>;
};

export default Orders;
