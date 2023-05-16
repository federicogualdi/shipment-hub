import React from 'react';
import { createUseStyles } from 'react-jss';
import { PackageDto } from '../../../api';
import { ReactComponent as IconStocked } from '../../../asset/svg/package-svgrepo-com.svg';
import { ReactComponent as Icon } from '../../../asset/svg/package-svgrepo-com-fill.svg';
import { PackageStatus } from '../../../api/rest/dto/enums';
import OpenOnMaps from '../../../shared/maps/OpenOnMaps';

const orderStyles = createUseStyles({
  package: {
    display: 'flex',
    fontSize: '1rem',
    marginTop: '0.5rem',
    marginBottom: '0.5rem'
  },
  'package-details': {
    fontSize: '1rem'
  },
  'icon-dimension': {
    height: '1rem',
    width: '1rem',
    marginRight: '0.3rem'
  }
});

function capitalizeFirstLetter(s: string) {
  s = s.replaceAll('_', ' ');
  return s.charAt(0).toUpperCase() + s.slice(1).toLocaleLowerCase();
}

const OrderItemPackage = (props: { package: PackageDto }) => {
  const styles = orderStyles();
  return (
    <>
      <section className={styles.package}>
        {(PackageStatus[props.package.status] as unknown) === PackageStatus.STOCKED ? (
          <IconStocked className={styles['icon-dimension']}></IconStocked>
        ) : (
          <Icon className={styles['icon-dimension']}></Icon>
        )}

        <span className={styles['package-details']}>
          {props.package.id} {capitalizeFirstLetter(props.package.status.toString())}
          <OpenOnMaps
            latitude={props.package.latitude}
            longitude={props.package.longitude}
          ></OpenOnMaps>
        </span>
      </section>
    </>
  );
};

export default OrderItemPackage;
