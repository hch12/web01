package com.example.web01.pojo;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeptTest {

    private static final LocalDateTime CREATED = LocalDateTime.of(2024, 1, 2, 3, 4, 5);
    private static final LocalDateTime UPDATED = LocalDateTime.of(2024, 6, 7, 8, 9, 10);

    @Test
    void noArgsConstructorLeavesFieldsNull() {
        Dept dept = new Dept();

        assertNull(dept.getId());
        assertNull(dept.getName());
        assertNull(dept.getCreateTime());
        assertNull(dept.getUpdateTime());
    }

    @Test
    void allArgsConstructorSetsEveryField() {
        Dept dept = new Dept(1, "研发部", CREATED, UPDATED);

        assertEquals(1, dept.getId());
        assertEquals("研发部", dept.getName());
        assertEquals(CREATED, dept.getCreateTime());
        assertEquals(UPDATED, dept.getUpdateTime());
    }

    @Test
    void settersUpdateFields() {
        Dept dept = new Dept();

        dept.setId(9);
        dept.setName("市场部");
        dept.setCreateTime(CREATED);
        dept.setUpdateTime(UPDATED);

        assertEquals(9, dept.getId());
        assertEquals("市场部", dept.getName());
        assertEquals(CREATED, dept.getCreateTime());
        assertEquals(UPDATED, dept.getUpdateTime());
    }

    @Test
    void equalsAndHashCodeUseAllFields() {
        Dept first = new Dept(1, "研发部", CREATED, UPDATED);
        Dept second = new Dept(1, "研发部", CREATED, UPDATED);
        Dept differentId = new Dept(2, "研发部", CREATED, UPDATED);
        Dept differentTime = new Dept(1, "研发部", CREATED, CREATED);

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
        assertNotEquals(first, differentId);
        assertNotEquals(first, differentTime);
        assertEquals(first, first);
        assertNotEquals(first, null);
        assertNotEquals(first, "not a dept");
    }

    @Test
    void toStringContainsFields() {
        String text = new Dept(1, "研发部", CREATED, UPDATED).toString();

        assertTrue(text.contains("id=1"));
        assertTrue(text.contains("name=研发部"));
        assertTrue(text.contains(CREATED.toString()));
        assertTrue(text.contains(UPDATED.toString()));
    }
}
