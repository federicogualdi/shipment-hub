import React from 'react';
import { createUseStyles } from 'react-jss';

type InputBox = {
  onChange: (value: string) => void;
  className: string;
  placeholder: string;
  style?: string;
  styleContainer?: string;
};

const inputBoxStyles = createUseStyles({
  field: {
    display: 'flex',
    justifyContent: 'center',
    margin: '2rem'
  },
  'field-combo': {
    margin: '2rem 0rem 2rem 2rem'
  },
  inputBox: {
    padding: '6px 12px',
    background: 'rgb(31, 32, 35)',
    border: '1px solid rgb(60, 63, 68)',
    borderRadius: '4px',
    fontSize: '1.5rem',
    color: 'rgb(247, 248, 248)',
    height: '46px',
    appearance: 'none',
    transition: 'border 0.15s ease 0s',
    '&:focus': {
      outline: 'none',
      boxShadow: 'none',
      borderColor: 'rgb(100, 153, 255)'
    }
  }
});

const InputBox = ({ onChange, className, placeholder, style, styleContainer }: InputBox) => {
  const styles = inputBoxStyles();

  const handleChange = (event: React.FormEvent<HTMLInputElement>) => {
    onChange(event.currentTarget.value);
    event?.preventDefault();
  };
  return (
    <div
      className={`${styles.field} ${className} ${
        styleContainer ? styles[styleContainer as keyof typeof style] : ''
      }`}
    >
      <input
        className={`${styles.inputBox} ${style}`}
        type="text"
        onChange={handleChange}
        placeholder={placeholder}
      />
    </div>
  );
};

export default InputBox;
