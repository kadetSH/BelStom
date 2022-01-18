package com.example.belstom

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.RelativeLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentManager
import com.example.belstom.databinding.ActivityCabinetBinding
import com.example.belstom.databinding.AppBarMainBinding
import com.example.belstom.jsonMy.AppointmentCreatedJS
import com.example.belstom.view.cabinet.feedback.fragment.FeedbackFragment
import com.example.belstom.view.cabinet.news.fragment.NewsDescriptionFragment
import com.example.belstom.view.cabinet.news.fragment.NewsFragment
import com.example.belstom.view.cabinet.news.recyclerNews.NewsItem
import com.example.belstom.view.cabinet.profile.fragment.ProfileFragment
import com.example.belstom.view.cabinet.receptions.fragment.ReceptionDescriptionFragment
import com.example.belstom.view.cabinet.receptions.fragment.ReceptionFragment
import com.example.belstom.view.cabinet.receptions.recyclerReceptions.ReceptionItem
import com.example.belstom.view.cabinet.schedule.fragment.*
import com.example.belstom.view.cabinet.visits.fragment.VisitsFragment
import com.google.android.gms.dynamic.SupportFragmentWrapper
import com.google.android.material.navigation.NavigationView
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.android.support.DaggerAppCompatActivity

class CabinetActivity : DaggerAppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    TitleController, NewsFragment.OpenNewsDescriptionItem,
    DepartmentsFragment.OnClickViewDepartment,
    DepartmentReceptionDaysFragment.OnDepartmentReceptionDaysClickListener,
    DepartmentsFragment.OnRequestDoctorsSchedule,
    FeedbackFragment.OpenFeedbackWWW, FeedbackFragment.DialingPhoneNumber,
    FeedbackFragment.SendLetter, ProfileFragment.ExitCabinet,
    ReceptionFragment.OpenReceptionItem, ProfileFragment.OpenMyMap,
    ProfileFragment.OpenMyVisits, BusinessHoursFragment.OpenViewAppointmentCreated,
    AppointmentCreatedFragment.OnAppointmentCreated {

    object Crashlytics {
        fun log(e: Throwable) {
            FirebaseCrashlytics.getInstance().recordException(e)
        }
    }

    companion object {
        const val EXTRA_SURNAME = "extra_surname"
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_PATRONYMIC = "extra_patronymic"
    }

    private lateinit var binding: ActivityCabinetBinding
    private lateinit var toolbarBinding: AppBarMainBinding
    private lateinit var header: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCabinetBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        toolbarBinding = binding.idAppBarPatientActivity

        checkIntent()

        //Без этого лога не отправляется отчет об ошибках
        MainActivity.Crashlytics.log(IllegalArgumentException())

        val toolbar = toolbarBinding.idToolbar
        setSupportActionBar(toolbar)
        val drawer = binding.drawerLayoutPatientActivity
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        toolbar.setTitleTextAppearance(this, R.style.MyStyleFont)  // Установка шрифта в тулбар
        //Слушаем нажатие в выпадающем меню
        binding.idNavigation.setNavigationItemSelectedListener(this)
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragments: MutableList<androidx.fragment.app.Fragment> = fragmentManager.fragments
        if (fragments.size == 0) {
            openContactInformation()
        }

    }

    private fun checkIntent() {
        val header0 = binding.idNavigation.getHeaderView(0)

//        intent.getStringExtra(EXTRA_SURNAME)?.let { surname ->
//            val titleSurname = header0.findViewById<TextView>(R.id.id_hm_edit_surname)
//            titleSurname.text = surname
//        }
//        intent.getStringExtra(EXTRA_NAME)?.let { name ->
//            val titleName = header0.findViewById<TextView>(R.id.id_hm_edit_name)
//            titleName.text = name
//        }
//        intent.getStringExtra(EXTRA_PATRONYMIC)?.let { patronymic ->
//            val titlePatronymic = header0.findViewById<TextView>(R.id.id_hm_edit_patronymic)
//            titlePatronymic.text = patronymic
//        }

        val heading = header0.findViewById<RelativeLayout>(R.id.id_header_menu)
        heading.setOnClickListener {
            binding.drawerLayoutPatientActivity.closeDrawer(GravityCompat.START)
            openContactInformation()
        }
    }

    private fun openContactInformation() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.FrameLayoutContainerContact, ProfileFragment())
            .addToBackStack(null)
            .commit()
    }

    private fun openNews() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.FrameLayoutContainerContact, NewsFragment())
            .addToBackStack(null)
            .commit()
    }

    private fun openFeedback() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.FrameLayoutContainerContact, FeedbackFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        when (title) {
            resources.getString(R.string.fragment_title_profile) -> {
                binding.drawerLayoutPatientActivity.openDrawer(GravityCompat.START)
            }
            resources.getString(R.string.fragment_title_news) -> {
                binding.drawerLayoutPatientActivity.openDrawer(GravityCompat.START)
            }
            resources.getString(R.string.fragment_department_title) -> {
                binding.drawerLayoutPatientActivity.openDrawer(GravityCompat.START)
            }
            resources.getString(R.string.fragment_title_reception) -> {
                binding.drawerLayoutPatientActivity.openDrawer(GravityCompat.START)
            }
            resources.getString(R.string.fragment_title_visits) -> {
                binding.drawerLayoutPatientActivity.openDrawer(GravityCompat.START)
            }
            resources.getString(R.string.fragment_title_feedback) -> {
                binding.drawerLayoutPatientActivity.openDrawer(GravityCompat.START)
            }


//            resources.getString(R.string.fragment_title_news_description) -> {
//                openNews()
//            }
//            resources.getString(R.string.fragment_department_title_department_therapeutic) -> {
//                openDepartmentsFragment()
//            }
//            resources.getString(R.string.fragment_department_title_department_orthopedic) -> {
//                openDepartmentsFragment()
//            }
//            resources.getString(R.string.fragment_department_title_department_LPO) -> {
//                openDepartmentsFragment()
//            }
//            resources.getString(R.string.fragment_department_title_department_surgery1) -> {
//                openDepartmentsFragment()
//            }
//            resources.getString(R.string.fragment_department_title_department_surgery2) -> {
//                openDepartmentsFragment()
//            }
//            resources.getString(R.string.fragment_department_title_department_LOR) -> {
//                openDepartmentsFragment()
//            }
//            ///////до сюда проверил
//
//            resources.getString(R.string.fragment_title_schedule_doctor_single) -> {
//                openDepartmentsFragment()
//            }
//            resources.getString(R.string.fragment_title_schedule_doctor_single_time) -> {
//                openDepartmentsFragment()
//            }
//            resources.getString(R.string.fragment_title_reception_description) -> {
//                openReceptionFragment()
//            }
//            resources.getString(R.string.fragment_list_department_label_orthopedic_speciality) -> {
//                openDepartmentsFragment()
//            }
//            resources.getString(R.string.fragment_list_department_label_therapeutic_speciality) -> {
//                openDepartmentsFragment()
//            }
//            resources.getString(R.string.fragment_list_department_label_surgery_speciality) -> {
//                openDepartmentsFragment()
//            }
//            resources.getString(R.string.fragment_list_department_label_periodontist_speciality) -> {
//                openDepartmentsFragment()
//            }
//            resources.getString(R.string.fragment_list_department_label_implantation_speciality) -> {
//                openDepartmentsFragment()
//            }
//            resources.getString(R.string.fragment_list_department_label_hygienist_speciality) -> {
//                openDepartmentsFragment()
//            }
//            resources.getString(R.string.fragment_list_department_label_orthodontist_speciality) -> {
//                openDepartmentsFragment()
//            }
//            resources.getString(R.string.fragment_list_department_label_LOR_speciality) -> {
//                openDepartmentsFragment()
//            }
            else -> {
//                binding.drawerLayoutPatientActivity.openDrawer(GravityCompat.START)
                val fm: FragmentManager = supportFragmentManager
                fm.popBackStack()
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.id_dm_profile -> openContactInformation()
            R.id.id_dm_news -> openNews()
            R.id.id_dm_appointment -> openDepartmentsFragment()
            R.id.id_dm_my_map -> openReceptionFragment()
            R.id.id_dm_my_visit -> openVisitsFragment()
            R.id.id_dm_feedback -> openFeedback()
        }
        binding.drawerLayoutPatientActivity.closeDrawer(GravityCompat.START)
        return true
    }

    override fun setTitle(titleIsFragment: String) {
        title = titleIsFragment
    }

    private fun openDepartmentsFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.FrameLayoutContainerContact,
                DepartmentsFragment()
            )
            .addToBackStack(null)
            .commit()
    }

    private fun openReceptionFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.FrameLayoutContainerContact,
                ReceptionFragment()
            )
            .addToBackStack(null)
            .commit()
    }

    private fun openVisitsFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.FrameLayoutContainerContact,
                VisitsFragment()
            )
            .addToBackStack(null)
            .commit()
    }

    override fun openNewsDescriptionItem(newsItem: NewsItem) {
        openNewsDescriptionFragment(newsItem)
    }

    private fun openNewsDescriptionFragment(newsItem: NewsItem) {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.FrameLayoutContainerContact,
                NewsDescriptionFragment.newInstance(newsItem)
            )
            .addToBackStack(null)
            .commit()
    }

    private fun openReceptionDescriptionFragment(receptionItem: ReceptionItem) {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.FrameLayoutContainerContact,
                ReceptionDescriptionFragment.newInstance(receptionItem)
            )
            .addToBackStack(null)
            .commit()
    }

    private fun openAppointmentCreated(answer: AppointmentCreatedJS) {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.FrameLayoutContainerContact,
                AppointmentCreatedFragment.newInstance(answer)
            )
            .addToBackStack(null)
            .commit()
    }

    private fun openDepartmentReceptionDaysFragment(department: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.FrameLayoutContainerContact,
                DepartmentReceptionDaysFragment.newInstance(department)
            )
            .addToBackStack(null)
            .commit()
    }

    override fun onClickDepartment(name: String) {
        openDepartmentReceptionDaysFragment(name)
    }

    override fun onDayClick(
        doctor: String,
        date: String,
        periodOfTime: String,
        department: String,
        singleBoolean: Boolean
    ) {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.FrameLayoutContainerContact,
                BusinessHoursFragment.newInstance(
                    doctor,
                    date,
                    periodOfTime,
                    department,
                    singleBoolean
                )
            )
            .addToBackStack(null)
            .commit()
    }

    override fun onRequestDoctorsSchedule(fio: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.FrameLayoutContainerContact,
                DepartmentReceptionDaysDoctorsFragment.newInstance(
                    fio
                )
            )
            .addToBackStack(null)
            .commit()
    }

    override fun openWWW(pathWWW: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(pathWWW))
        startActivity(intent)
    }

    override fun dialingNumber(number: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${number}"))
        startActivity(intent)
    }

    override fun sendLetter(email: String) {
        val emailIntent = Intent(
            Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", email, null
            )
        )
        startActivity(Intent.createChooser(emailIntent, "Письмо в стомат. поликлинику"))
    }

    override fun clickExitCabinet() {
        finish()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun openReceptionDescriptionItem(receptionItem: ReceptionItem) {
        openReceptionDescriptionFragment(receptionItem)
    }

    override fun clickOpenMyMap() {
        openReceptionFragment()
    }

    override fun clickOpenMyVisits() {
        openVisitsFragment()
    }

    override fun onCreated(answerAppointment: AppointmentCreatedJS) {
        openAppointmentCreated(answerAppointment)
    }

    override fun onClickAllAppointment() {
        openVisitsFragment()
    }


}