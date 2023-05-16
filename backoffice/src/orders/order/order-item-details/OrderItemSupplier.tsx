import React from 'react';
import { createUseStyles } from 'react-jss';
import { SupplierDto } from '../../../api';

const orderStyles = createUseStyles({
  supplier: {
    display: 'flex',
    flexDirection: 'column'
  },
  'supplier-header': {
    color: 'grey',
    fontSize: 'medium'
  }
});

const OrderItemSupplier = (props: { supplier: SupplierDto }) => {
  const styles = orderStyles();
  return (
    <>
      <section className={styles.supplier}>
        <span className={styles['supplier-header']}>Created by:</span>
        {props.supplier.name}
      </section>
    </>
  );
};

export default OrderItemSupplier;
