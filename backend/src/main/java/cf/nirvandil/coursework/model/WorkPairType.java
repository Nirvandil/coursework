package cf.nirvandil.coursework.model;

public enum  WorkPairType {
    EXAM, REGULAR;

    @Override
    public String toString() {
        switch (this) {
            case EXAM:
                return "Экзамен";
            case REGULAR:
                return "Пара";
        }
        return null;
    }
}
