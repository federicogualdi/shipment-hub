import { useEffect, useCallback } from 'react';

/**
 * Implement debounce function
 *
 * @param effect - Callback function
 * @param dependencies - Dependencies that triggers callback
 * @param delay - amount of ms to wait from last 'dependencies' changes before trigger callback function
 */
export default function useDebounce(effect: () => void, dependencies: unknown[], delay: number) {
  const callback = useCallback(effect, dependencies);

  useEffect(() => {
    const timeout = setTimeout(callback, delay);
    return () => clearTimeout(timeout);
  }, [callback, delay]);
}
