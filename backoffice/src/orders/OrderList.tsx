import React from 'react';
import { createUseStyles } from 'react-jss';
import { Order } from '../api';
import OrderItem from './order/OrderItem';

const orderStyles = createUseStyles({
  orders: {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    flexWrap: 'wrap',
    maxWidth: '1920px',
    margin: '1.5rem 2rem 1.5rem 2rem'
  }
});

const listItems = (orders: Order[]) =>
  orders.map((order) => <OrderItem key={order.id} order={order} />);

const OrderList = (orders: Order[]) => {
  const styles = orderStyles();
  return <div className={styles.orders}>{listItems(orders)}</div>;
};

export default OrderList;
