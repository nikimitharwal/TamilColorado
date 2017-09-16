package in.com.decipherzone.tamilcolorado.fragment.base;

/**
 * Created by admin on 23/7/2015.
 */
public enum FragmentNames {
    HomeFragment(0),
    MembershipFragment(1),
    HistoryFragment(2),
    CommitteeFragment(3);

    private final int value;

    FragmentNames(final int newValue) {
        value = newValue;
    }

    public int getValue() {
        return value;
    }

}
