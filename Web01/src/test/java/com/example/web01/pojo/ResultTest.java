package com.example.web01.pojo;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ResultTest {

    @Test
    void successWithoutDataHasCodeOneAndNoData() {
        Result result = Result.success();

        assertEquals(1, result.getCode());
        assertEquals("success", result.getMsg());
        assertNull(result.getData());
    }

    @Test
    void successWithDataKeepsPayload() {
        List<String> data = List.of("a", "b");

        Result result = Result.success(data);

        assertEquals(1, result.getCode());
        assertEquals("success", result.getMsg());
        assertEquals(data, result.getData());
    }

    @Test
    void successAcceptsNullData() {
        Result result = Result.success(null);

        assertEquals(1, result.getCode());
        assertEquals("success", result.getMsg());
        assertNull(result.getData());
    }

    @Test
    void errorHasCodeZeroAndGivenMessage() {
        Result result = Result.error("boom");

        assertEquals(0, result.getCode());
        assertEquals("boom", result.getMsg());
        assertNull(result.getData());
    }

    @Test
    void settersOverrideValues() {
        Result result = Result.success("data");

        result.setCode(0);
        result.setMsg("changed");
        result.setData(null);

        assertEquals(0, result.getCode());
        assertEquals("changed", result.getMsg());
        assertNull(result.getData());
    }

    @Test
    void equalsAndHashCodeUseAllFields() {
        Result first = Result.success("same");
        Result second = Result.success("same");
        Result other = Result.error("nope");

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
        assertNotEquals(first, other);
        assertEquals(first, first);
        assertNotEquals(first, null);
        assertNotEquals(first, "not a result");
    }

    @Test
    void toStringContainsFields() {
        String text = Result.success("payload").toString();

        assertTrue(text.contains("code=1"));
        assertTrue(text.contains("msg=success"));
        assertTrue(text.contains("data=payload"));
    }
}
