import React from 'react';
import { createUseStyles } from 'react-jss';
import OrderItemPackage from './OrderItemPackage';
import { PackageDto } from '../../../api';

const orderStyles = createUseStyles({});

const listItems = (packages: PackageDto[]) =>
  packages.map((p) => <OrderItemPackage key={p.id} package={p} />);

const OrderItemPackages = (props: { packages: PackageDto[] }) => {
  const styles = orderStyles();
  return (
    <>
      <section>{listItems(props.packages)}</section>
    </>
  );
};

export default OrderItemPackages;
