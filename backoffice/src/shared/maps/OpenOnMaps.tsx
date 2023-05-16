import React from 'react';
import { createUseStyles } from 'react-jss';

const orderStyles = createUseStyles({
  link: {
    marginLeft: '0.5rem',
    color: 'gold',
    fontSize: 'small',
    textDecoration: 'none'
  }
});

const OpenOnMaps = (props: { latitude: number; longitude: number }) => {
  const styles = orderStyles();
  const mapsURL = `http://maps.google.com/maps?t=h&q=loc:${props.latitude}+${props.longitude}`;
  return (
    <>
      <a className={styles.link} href={mapsURL} target="blank">
        Open on Maps
      </a>
    </>
  );
};

export default OpenOnMaps;
