package brachy.modularui.value;

import brachy.modularui.api.value.IValue;

import lombok.Getter;
import lombok.Setter;

/**
 * @deprecated use {@link ObjectValue} instead
 */
@Deprecated
public class ConstValue<T> implements IValue<T> {

    @Getter
    @Setter
    protected T value;

    public ConstValue(T value) {
        this.value = value;
    }

    @Override
    @SuppressWarnings("unchecked") // getClass() runtime type of the stored T value
    public Class<T> getValueType() {
        return (Class<T>) value.getClass();
    }
}
