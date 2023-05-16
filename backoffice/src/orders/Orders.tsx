import React, { useEffect, useState } from 'react';
import { createUseStyles } from 'react-jss';
import OrderList from './OrderList';
import { OrderDto, getOrders } from '../api';
import { OrderFilter } from '../api/rest/dto/OrderFilter';
import DebouncedInputBox from '../shared/input-box/DebouncedInputBox';
import SelectBox from '../shared/input-box/SelectBox';

const orderStyles = createUseStyles({
  'orders-container': {
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'center',
    alignItems: 'center',
    background: 'rgb(32, 35, 41)',
    minHeight: '60vh'
  },
  filters: {
    display: 'flex',
    flexDirection: 'row',
    flexFlow: 'wrap',
    justifyContent: 'center'
  },
  'filters-tiny': {
    maxWidth: '6rem'
  }
});
const Orders = () => {
  const [orders, setOrders] = useState<OrderDto[]>([]);

  const [orderBy, setOrderBy] = useState<string>();
  const [orderDirection, setOrderDirection] = useState<'asc' | 'desc'>('asc');
  const [skip, setSkip] = useState(0);
  const [top, setTop] = useState(0);

  useEffect(() => {
    const orderApi = async (filters?: OrderFilter) =>
      getOrders(filters)
        .then((res) => {
          setOrders(res ?? []);
        })
        .catch((err) => console.error(err));

    orderApi({
      orderBy: orderBy,
      orderDirection: orderDirection,
      skip: skip,
      top: top
    });
  }, [orderBy, orderDirection, skip, top]);

  const onChangeOrderByFilter = (value: string) => {
    setOrderBy(value);
  };

  const onChangeOrderDirection = (value: string) => {
    setOrderDirection(value as 'asc' | 'desc');
  };

  const onChangeSkip = (value: string) => {
    const skip: number = !isNaN(+value) ? +value : 0;
    setSkip(skip);
  };

  const onChangeTop = (value: string) => {
    const top: number = !isNaN(+value) ? +value : 0;
    setTop(top);
  };

  const styles = orderStyles();
  return (
    <>
      <section className={styles.filters}>
        <DebouncedInputBox
          needStickStyle={false}
          setDebouncedValue={onChangeOrderByFilter}
          placeholder="Order by"
          style={styles['filters-tiny']}
          styleContainer={'field-combo'}
        ></DebouncedInputBox>
        <SelectBox
          onChange={onChangeOrderDirection}
          placeholder="Order direction"
          style={styles['filters-tiny']}
          options={[{ value: 'asc' }, { value: 'desc' }]}
        ></SelectBox>
        <DebouncedInputBox
          needStickStyle={false}
          setDebouncedValue={onChangeSkip}
          placeholder="skip"
          style={styles['filters-tiny']}
        ></DebouncedInputBox>
        <DebouncedInputBox
          needStickStyle={false}
          setDebouncedValue={onChangeTop}
          placeholder="top"
          style={styles['filters-tiny']}
        ></DebouncedInputBox>
      </section>
      <section className={styles['orders-container']}>
        <OrderList orders={orders}></OrderList>
      </section>
    </>
  );
};

export default Orders;
