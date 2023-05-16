import React from 'react';
import { createUseStyles } from 'react-jss';
import { DepotDto } from '../../../api';
import OpenOnMaps from '../../../shared/maps/OpenOnMaps';

const orderStyles = createUseStyles({
  depot: {
    display: 'flex',
    flexDirection: 'column',
    '& $span': {
      marginBottom: '0.1rem'
    }
  },
  'depot-header': {
    color: 'grey',
    fontSize: 'medium'
  },
  'depot-data': {
    display: 'flex',
    alignItems: 'center',
    '& $a': {
      marginLeft: '0.5rem',
      color: 'gold',
      fontSize: 'small',
      textDecoration: 'none'
    }
  }
});

const OrderItemDepot = (props: { depot: DepotDto }) => {
  const styles = orderStyles();
  return (
    <>
      <section className={styles.depot}>
        <span className={styles['depot-header']}>Depot</span>
        <section className={styles['depot-data']}>
          <span>{props.depot.name}</span>
          <OpenOnMaps
            latitude={props.depot.latitude}
            longitude={props.depot.longitude}
          ></OpenOnMaps>
        </section>
      </section>
    </>
  );
};

export default OrderItemDepot;
