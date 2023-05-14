import React from 'react';
import { createUseStyles } from 'react-jss';
import { Order } from '../../api';

const orderStyles = createUseStyles({});

const OrderItem = (props: { order: Order }) => {
  const styles = orderStyles();
  return <></>;
};

export default OrderItem;
