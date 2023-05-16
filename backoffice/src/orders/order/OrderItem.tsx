import React from 'react';
import { createUseStyles } from 'react-jss';
import { OrderDto } from '../../api';
import OrderItemDepot from './order-item-details/OrderItemDepot';
import OrderItemPackages from './order-item-details/OrderItemPackages';
import OrderItemSupplier from './order-item-details/OrderItemSupplier';

const orderStyles = createUseStyles({
  order: {
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'space-between',

    background: 'rgb(60, 62, 68)',
    borderRadius: '0.5rem',
    margin: '0.75rem',
    padding: '2rem',
    boxShadow: 'rgb(0 0 0 / 10%) 0px 4px 6px -1px, rgb(0 0 0 / 6%) 0px 2px 4px -1px',
    width: '80vw',
    maxWidth: '600px',
    height: '220px',
    fontSize: '1.5rem',

    color: 'white'
  },
  'order-header': {
    display: 'flex',
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    '& $span': {
      color: 'grey',
      fontSize: 'medium'
    }
  },
  'depot-package': {
    display: 'flex',
    flexDirection: 'row',
    justifyContent: 'space-evenly'
  }
});

const OrderItem = (props: { order: OrderDto }) => {
  const styles = orderStyles();
  return (
    <>
      <section className={styles.order}>
        <section className={styles['order-header']}>
          <section>
            <span>Order Nr.</span> {props.order.id}
          </section>
          <OrderItemSupplier supplier={props.order.supplier}></OrderItemSupplier>
        </section>
        <section className={styles['depot-package']}>
          <OrderItemDepot depot={props.order.depot}></OrderItemDepot>
          <OrderItemPackages packages={props.order.packages}></OrderItemPackages>
        </section>
      </section>
    </>
  );
};

export default OrderItem;
