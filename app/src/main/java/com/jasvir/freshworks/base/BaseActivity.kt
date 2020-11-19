package com.jasvir.freshworks.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

/**
 * BaseActivity for activity's
 */
abstract class BaseActivity(private val backButton: Boolean) : AppCompatActivity() {
    @LayoutRes
    abstract fun getLayoutResId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        if (backButton) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }


}