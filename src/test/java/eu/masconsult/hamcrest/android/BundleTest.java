package eu.masconsult.hamcrest.android;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;

import android.os.Bundle;

import com.xtremelabs.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class BundleTest {

	@Test
	public void shouldMatchKeySet() {
		Bundle bundle = new Bundle();
		bundle.putBoolean("alabala", false);
		assertThat(bundle, BundleKeySet.keySet(sameInstance(bundle.keySet())));
	}

	@Test
	public void shouldMatchBundleValue() {
		Bundle bundle = new Bundle();
		bundle.putBoolean("alabala", false);
		assertThat(bundle, BundleValue.value("alabala", equalTo(false)));
	}

	@Test
	public void shouldMatchSimilarBundles() {
		Bundle bundle1 = new Bundle();
		bundle1.putString("key1", "value1");
		Bundle bundle2 = new Bundle();
		bundle2.putString("key1", "value1");

		assertThat(bundle1, MatchesBundle.matchesBundle(bundle2));
	}

	@Test
	public void shouldNotMatchDifferentBundles() {
		Bundle bundle1 = new Bundle();
		bundle1.putString("key1", "value1");
		Bundle bundle2 = new Bundle();
		bundle2.putString("key2", "value2");

		assertThat(bundle1, not(MatchesBundle.matchesBundle(bundle2)));
	}

	@Test
	public void shouldNotMatchOverlappingBundles() {
		Bundle bundle1 = new Bundle();
		bundle1.putString("key1", "value1");
		Bundle bundle2 = new Bundle();
		bundle2.putString("key1", "value1");
		bundle2.putString("key2", "value2");

		assertThat(bundle1, not(MatchesBundle.matchesBundle(bundle2)));
		assertThat(bundle2, not(MatchesBundle.matchesBundle(bundle1)));
	}

	@Test
	public void shouldNotMatchBundlesWithDifferentTypeValues() {
		Bundle bundle1 = new Bundle();
		bundle1.putString("key1", "123");
		Bundle bundle2 = new Bundle();
		bundle2.putFloat("key1", 123);

		assertThat(bundle1, not(MatchesBundle.matchesBundle(bundle2)));
		assertThat(bundle2, not(MatchesBundle.matchesBundle(bundle1)));
	}
}
