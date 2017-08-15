package model;

import java.util.List;
import java.util.Optional;

/**
 * Created by eglushchenko on 15.08.2017.
 */
public interface History  {

    public void push(HistoryItem item);

    public Optional<List<HistoryItem>> getAll();
}
