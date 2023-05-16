import React from 'react';
import { createUseStyles } from 'react-jss';
import { OrderDto } from '../api';
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

const listItems = (orders: OrderDto[]) =>
  orders.map((order) => <OrderItem key={order.id} order={order} />);

const OrderList = (props: { orders: OrderDto[] }) => {
  const styles = orderStyles();
  return <div className={styles.orders}>{listItems(props.orders)}</div>;
};

export default OrderList;
