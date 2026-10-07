package brachy.modularui.theme;

import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;

public class WidgetThemeMap extends Object2ObjectLinkedOpenHashMap<WidgetThemeKey<?>, WidgetThemeEntry<?>> {

    // serialver-computed (JDK 21, pinned to the implicit UID this class already deserializes with)
    private static final long serialVersionUID = -1841408271264695038L;

    @Override
    public WidgetThemeEntry<?> put(WidgetThemeKey<?> widgetThemeKey, WidgetThemeEntry<?> widgetTheme) {
        if (widgetThemeKey != widgetTheme.key()) {
            throw new IllegalArgumentException(widgetThemeKey.getFullName() + " is not compatible with " +
                    widgetTheme.key().getFullName());
        }
        return super.put(widgetThemeKey, widgetTheme);
    }

    public <T extends WidgetTheme> void putTheme(WidgetThemeKey<T> key, WidgetThemeEntry<T> widgetTheme) {
        super.put(key, widgetTheme);
    }

    @SuppressWarnings("unchecked")
    public <T extends WidgetTheme> WidgetThemeEntry<T> getTheme(WidgetThemeKey<T> widgetThemeKey) {
        return (WidgetThemeEntry<T>) super.get(widgetThemeKey);
    }

    public <T extends WidgetTheme> void register(WidgetThemeKey<T> key, T widgetTheme, T hoverWidgetTheme) {
        putTheme(key, new WidgetThemeEntry<>(key, widgetTheme, hoverWidgetTheme));
    }
}
