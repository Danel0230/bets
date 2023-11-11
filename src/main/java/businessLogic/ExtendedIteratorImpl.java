package businessLogic;

import java.util.List;

public class ExtendedIteratorImpl<T> implements ExtendedIterator<T> {
    private List<T> elements;
    private int currentIndex = 0;

    public ExtendedIteratorImpl(List<T> elements) {
        this.elements = elements;
    }

    public T previous() {
        if (hasPrevious()) {
            return elements.get(currentIndex--);
        }
        return null;
    }

    public boolean hasPrevious() {
        return currentIndex > 0;
    }

    public void goFirst() {
        currentIndex = 0;
    }

    public void goLast() {
        currentIndex = elements.size() - 1;
    }

    public boolean hasNext() {
        return currentIndex < elements.size();
    }

    public T next() {
        if (hasNext()) {
            return elements.get(currentIndex++);
        }
        return null;
    }
}
