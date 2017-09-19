package com.weproud.validator;


import com.weproud.exception.ServiceException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author Logan.k
 */
public final class Preconditions {


    private Preconditions() {
        throw new AssertionError("No \"Ensure\" instances for you!");
    }

    public static <T> T requireNonNull(T t, final Supplier<? extends Exception> supplier) throws Exception {
        if (t == null)
            throw supplier.get();
        return t;
    }

    public static String requireNonEmpty(final CharSequence cs, final Supplier<? extends Exception> supplier) throws Exception {
        if (cs == null || cs.length() == 0)
            throw supplier.get();
        return (String) cs;
    }

    public static <T> Collection<T> requireNonEmpty(final Collection<T> collection, final Supplier<? extends Exception> supplier) throws Exception {
        if (collection == null || collection.isEmpty())
            throw supplier.get();
        return collection;
    }

    public static <K, V> Map<K, V> requireNonEmpty(final Map<K, V> map, final Supplier<? extends Exception> supplier) throws Exception {
        if (map == null || map.isEmpty())
            throw supplier.get();
        return map;
    }

    public static void ifThenThrow(final Boolean ifThen, final Supplier<? extends Exception> supplier) throws Exception {
        if (ifThen)
            throw supplier.get();
    }

    public static <E extends Enum<E>> E requireContainsEnum(final String value, final Class<E> enumClass) throws Exception {
        if (Arrays.stream(enumClass.getEnumConstants()).noneMatch(e -> e.name().equals(value)))
            throw new ServiceException("ValueNotContainsEnumError", String.format("\'%s\'는 잘못된 값 입니다.", value));
        return Enum.valueOf(enumClass, value);
    }

    public static <T> void withPredicate(final Predicate<T> predicate, T t, final Supplier<? extends Exception> exception) throws Exception {
        if (predicate.test(t))
            throw exception.get();
    }

    public static <T, U> void withBiPredicate(final BiPredicate<T, U> predicate, T t, U u, final Supplier<? extends Exception> exception) throws Exception {
        if (predicate.test(t, u))
            throw exception.get();
    }
}