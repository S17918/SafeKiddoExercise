package com.safekiddo.exercise.presentation.ui.post_add

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.safekiddo.exercise.R
import com.safekiddo.exercise.domain.model.Post
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostAddFragment : Fragment() {

    private val viewModel: PostAddViewModel by viewModels()
    private lateinit var addImageButton: Button
    private lateinit var submitButton: Button
    private lateinit var cancelButton: Button
    private lateinit var selectedImage: Uri
    private lateinit var imagePreview: ImageView
    private lateinit var titleEdit: EditText
    private lateinit var descriptionEdit: EditText

    private val SELECT_PICRUTE = 700

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.post_add_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initButtons()
        initImageViews()
        initEditTexts()

        addImageButton.setOnClickListener { setImage() }
        cancelButton.setOnClickListener{ onCancelAction() }
        submitButton.setOnClickListener{ onSubmitAction() }

    }

    private fun setImage() {
        val intent: Intent = Intent()
        intent.action = Intent.ACTION_OPEN_DOCUMENT
        intent.type = "image/*"
        intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivityForResult(Intent.createChooser(intent, "Select Image"), SELECT_PICRUTE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == RESULT_OK){
            if(requestCode == SELECT_PICRUTE){
                selectedImage = data!!.data!!
                context?.contentResolver?.takePersistableUriPermission(selectedImage, Intent.FLAG_GRANT_READ_URI_PERMISSION)
                imagePreview.setImageURI(selectedImage)
            }
        }
    }

    private fun onSubmitAction() {
        val title: String = titleEdit.text.toString()
        val description: String = descriptionEdit.text.toString()
        val image: String = selectedImage.toString()

        val post: Post = Post(title, description, image, false)
        viewModel.insertPost(post)

        val bundle: Bundle = Bundle()
        bundle.putParcelable("post", post)

        findNavController().popBackStack(R.id.postListFragment, false)
        findNavController().navigate(R.id.showPostAction, bundle)
    }

    private fun onCancelAction() {
        findNavController().popBackStack(R.id.postListFragment, false)
    }

    private fun initButtons(){
        addImageButton = view?.findViewById(R.id.add_image_button)!!
        submitButton = view?.findViewById(R.id.add_button_submit)!!
        cancelButton = view?.findViewById(R.id.add_button_cancel)!!
    }

    private fun initImageViews(){
        imagePreview = view?.findViewById(R.id.add_post_icon)!!
    }

    private fun initEditTexts(){
        titleEdit = view?.findViewById(R.id.add_post_title)!!
        descriptionEdit = view?.findViewById(R.id.add_post_description)!!
    }

}