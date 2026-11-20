def add(a: float, b: float) -> float:
    return a + b


def divide(a: float, b: float) -> float:
    if b == 0:
        raise ZeroDivisionError("除数不能为 0")
    return a / b
