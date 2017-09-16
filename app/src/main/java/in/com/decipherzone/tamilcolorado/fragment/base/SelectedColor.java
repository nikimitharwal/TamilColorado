package in.com.decipherzone.tamilcolorado.fragment.base;

/**
 * Created by admin on 23/7/2015.
 */
public enum SelectedColor {
    OrangeColor(0),
    BlueColor(1),
    GreenColor(2),
    GrayColor(3);

    private final int value;

    SelectedColor(final int newValue) {
        value = newValue;
    }

    public int getValue() {
        return value;
    }

}
