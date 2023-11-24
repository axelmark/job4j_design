package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void isThisGetNumberOfVerticesZero() {
        Box box = new Box(0, 1);
        int number = box.getNumberOfVertices();
        assertThat(number).isEqualTo(0);
    }

    @Test
    void isThisGetNumberOfVerticesTrue() {
        Box box = new Box(0, 1);
        int number = box.getNumberOfVertices();
        assertThat(number).isLessThan(1);
    }

    @Test
    void isThisExistTrue() {
        Box box = new Box(0, 1);
        boolean res = box.isExist();
        assertThat(res).isTrue();
    }

    @Test
    void isThisExistFalse() {
        Box box = new Box(1, 1);
        boolean res = box.isExist();
        assertThat(res).isFalse();
    }

    @Test
    void isSphereAreaEquals() {
        Box box = new Box(0, 10);
        double area = box.getArea();
        assertThat(area).isCloseTo(1256.63, offset(0.01D));

    }

    @Test
    void isAreaEqual2() {
        Box box = new Box(4, 7);
        double area = box.getArea();
        assertThat(area).isCloseTo(84.87, offset(0.01D));
    }
}