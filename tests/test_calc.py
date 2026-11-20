import pytest

from src.calc import add, divide


@pytest.mark.parametrize(
    "a,b,expected",
    [(1, 2, 3), (0, 0, 0), (-1, 1, 0), (1.5, 2.5, 4.0)],
)
def test_add(a, b, expected):
    assert add(a, b) == expected


def test_divide_ok():
    assert divide(10, 4) == 2.5


def test_divide_by_zero():
    with pytest.raises(ZeroDivisionError):
        divide(1, 0)
