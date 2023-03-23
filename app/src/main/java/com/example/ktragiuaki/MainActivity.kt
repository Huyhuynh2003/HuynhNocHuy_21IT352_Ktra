package com.example.ktragiuaki

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ktragiuaki.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    lateinit var db: SQLiteDatabase
    lateinit var rs: Cursor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var helper=myHelper(applicationContext)
        db=helper.readableDatabase
        rs=db.rawQuery("Select * from Users Limit 20",null)
        binding.button.setOnClickListener {
            if(binding.edtPass.text.toString().compareTo(binding.edtPass2.text.toString())==0 ){
                var cv=ContentValues()
                cv.put("email", binding.edtEmail.text.toString())
                cv.put("userName", binding.edtName.text.toString())
                cv.put("password", binding.edtPass.text.toString())
                db.insert("Users",null,cv)
                rs.requery()
                binding.edtEmail.setText("")
                binding.edtName.setText("")
                binding.edtPass.setText("")
                binding.edtPass2.setText("")
                Toast.makeText(this,"Đăng kí thành công",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Đăng kí thất bại",Toast.LENGTH_SHORT).show()
            }
        }
    }
}