package eu.masconsult.hamcrest.android;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.isIn;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import android.os.Bundle;

public class MatchesBundle extends TypeSafeMatcher<Bundle> {

	private List<Matcher<? super Bundle>> matchers;
	private Bundle expected;
	private Matcher<? extends Bundle> matcher;

	public MatchesBundle(Bundle expected) {
		super(Bundle.class);
		this.expected = expected;
		matchers = new ArrayList<Matcher<? super Bundle>>();
		matchKeys();
		matchValues();
		matcher = allOf(matchers);
	}

	private void matchKeys() {
		String[] expectedKeySetArray = expected.keySet().toArray(
				new String[] {});

		Matcher<String> in = isIn(expectedKeySetArray);
		Matcher<Iterable<String>> allKeysAreExpected = everyItem(in);

		Matcher<Iterable<? extends String>> hasAllExpectedKeysUnbound = contains(expectedKeySetArray);
		Matcher<Iterable<String>> hasAllExpectedKeys = MatcherWrapper
				.wrap(hasAllExpectedKeysUnbound);

		matchers.add(BundleKeySet.keySet(allOf(allKeysAreExpected,
				hasAllExpectedKeys)));
	}

	private void matchValues() {
		for (String key : expected.keySet()) {
			matchers.add(BundleValue.value(key, equalTo(expected.get(key))));
		}
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("match expected bundle").appendDescriptionOf(
				matcher);
	}

	@Override
	protected boolean matchesSafely(Bundle item) {
		return matcher.matches(item);
	}
}
