package com.example.dicodingnavigation;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavArgs;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class DetailCategoryFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private DetailCategoryFragmentArgs() {
  }

  @SuppressWarnings("unchecked")
  private DetailCategoryFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static DetailCategoryFragmentArgs fromBundle(@NonNull Bundle bundle) {
    DetailCategoryFragmentArgs __result = new DetailCategoryFragmentArgs();
    bundle.setClassLoader(DetailCategoryFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("name")) {
      String name;
      name = bundle.getString("name");
      if (name == null) {
        throw new IllegalArgumentException("Argument \"name\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("name", name);
    } else {
      __result.arguments.put("name", "default name");
    }
    if (bundle.containsKey("stock")) {
      long stock;
      stock = bundle.getLong("stock");
      __result.arguments.put("stock", stock);
    } else {
      __result.arguments.put("stock", 0L);
    }
    return __result;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static DetailCategoryFragmentArgs fromSavedStateHandle(
      @NonNull SavedStateHandle savedStateHandle) {
    DetailCategoryFragmentArgs __result = new DetailCategoryFragmentArgs();
    if (savedStateHandle.contains("name")) {
      String name;
      name = savedStateHandle.get("name");
      if (name == null) {
        throw new IllegalArgumentException("Argument \"name\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("name", name);
    } else {
      __result.arguments.put("name", "default name");
    }
    if (savedStateHandle.contains("stock")) {
      long stock;
      stock = savedStateHandle.get("stock");
      __result.arguments.put("stock", stock);
    } else {
      __result.arguments.put("stock", 0L);
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public String getName() {
    return (String) arguments.get("name");
  }

  @SuppressWarnings("unchecked")
  public long getStock() {
    return (long) arguments.get("stock");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("name")) {
      String name = (String) arguments.get("name");
      __result.putString("name", name);
    } else {
      __result.putString("name", "default name");
    }
    if (arguments.containsKey("stock")) {
      long stock = (long) arguments.get("stock");
      __result.putLong("stock", stock);
    } else {
      __result.putLong("stock", 0L);
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public SavedStateHandle toSavedStateHandle() {
    SavedStateHandle __result = new SavedStateHandle();
    if (arguments.containsKey("name")) {
      String name = (String) arguments.get("name");
      __result.set("name", name);
    } else {
      __result.set("name", "default name");
    }
    if (arguments.containsKey("stock")) {
      long stock = (long) arguments.get("stock");
      __result.set("stock", stock);
    } else {
      __result.set("stock", 0L);
    }
    return __result;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
        return true;
    }
    if (object == null || getClass() != object.getClass()) {
        return false;
    }
    DetailCategoryFragmentArgs that = (DetailCategoryFragmentArgs) object;
    if (arguments.containsKey("name") != that.arguments.containsKey("name")) {
      return false;
    }
    if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) {
      return false;
    }
    if (arguments.containsKey("stock") != that.arguments.containsKey("stock")) {
      return false;
    }
    if (getStock() != that.getStock()) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (getName() != null ? getName().hashCode() : 0);
    result = 31 * result + (int)(getStock() ^ (getStock() >>> 32));
    return result;
  }

  @Override
  public String toString() {
    return "DetailCategoryFragmentArgs{"
        + "name=" + getName()
        + ", stock=" + getStock()
        + "}";
  }

  public static final class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(@NonNull DetailCategoryFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    public Builder() {
    }

    @NonNull
    public DetailCategoryFragmentArgs build() {
      DetailCategoryFragmentArgs result = new DetailCategoryFragmentArgs(arguments);
      return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setName(@NonNull String name) {
      if (name == null) {
        throw new IllegalArgumentException("Argument \"name\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("name", name);
      return this;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setStock(long stock) {
      this.arguments.put("stock", stock);
      return this;
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    @NonNull
    public String getName() {
      return (String) arguments.get("name");
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    public long getStock() {
      return (long) arguments.get("stock");
    }
  }
}
