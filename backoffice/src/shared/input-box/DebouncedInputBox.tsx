import React, { useState } from 'react';
import { createUseStyles } from 'react-jss';
import InputBox from './InputBox';
import useDebounce from '../utils/Debounce.utils';

type DebouncedInputBox = {
  needStickStyle: boolean;
  setDebouncedValue: (value: string) => void;
  placeholder: string;
  style?: string;
  styleContainer?: string;
  defaultValue?: string;
};

const debouncedInputBoxStyles = createUseStyles({
  inputBox: {
    position: 'sticky',
    top: '1rem',
    zIndex: 10
  }
});

const DebouncedInputBox = ({
  needStickStyle,
  setDebouncedValue,
  placeholder,
  style,
  styleContainer
}: DebouncedInputBox) => {
  const [value, setvalue] = useState<string>();

  useDebounce(() => setDebouncedValue(value ?? ''), [value], 250);

  const styles = debouncedInputBoxStyles();
  return (
    <InputBox
      className={needStickStyle ? `${styles.inputBox}` : ``}
      onChange={setvalue}
      placeholder={placeholder}
      style={style}
      styleContainer={styleContainer}
    ></InputBox>
  );
};

export default DebouncedInputBox;
