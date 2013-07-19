package eu.masconsult.hamcrest.android;

import static org.hamcrest.Matchers.equalTo;
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
}
