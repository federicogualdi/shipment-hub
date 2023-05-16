import React from 'react';
import { createUseStyles } from 'react-jss';

type SelectBox = {
  onChange: (value: string) => void;
  className?: string;
  placeholder: string;
  style?: string;
  options: Option[];
};
type Option = {
  value: string;
  text?: string;
};

const selectBoxStyles = createUseStyles({
  field: {
    display: 'flex',
    justifyContent: 'center',
    margin: '2rem 2rem 2rem 0rem'
  },
  selectBox: {
    padding: '6px 12px',
    background: 'rgb(31, 32, 35)',
    border: '1px solid rgb(60, 63, 68)',
    borderRadius: '4px',
    fontSize: '1.5rem',
    color: 'rgb(247, 248, 248)',
    appearance: 'none',
    transition: 'border 0.15s ease 0s',
    '&:focus': {
      outline: 'none',
      boxShadow: 'none',
      borderColor: 'rgb(100, 153, 255)'
    }
  }
});

const computeOptions = (options: Option[]) => {
  return options.map((option) => (
    <option key={option.value} value={option.value}>
      {option.value}
    </option>
  ));
};

const SelectBox = ({ onChange, className, placeholder, style, options }: SelectBox) => {
  const styles = selectBoxStyles();

  const handleChange = (event: React.FormEvent<HTMLSelectElement>) => {
    onChange(event.currentTarget.value);
    event?.preventDefault();
  };

  return (
    <div className={`${styles.field} ${className}`}>
      <select
        className={`${styles.selectBox} ${style}`}
        onChange={handleChange}
        placeholder={placeholder}
      >
        {computeOptions(options)}
      </select>
    </div>
  );
};

export default SelectBox;
