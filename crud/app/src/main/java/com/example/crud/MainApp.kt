package com.example.crud

data class DetailInfo(val email: String, val asalKota: String)

class Mahasiswa(val nim: String, namaInput: String) {

    var nama: String = namaInput
        get() = field.uppercase()
        set(value) {
            field = if (value.isNotBlank()) value.trim() else "TANPA NAMA"
        }

    var detail: DetailInfo? = null
}

fun main() {
    val databaseMahasiswa: ArrayList<Mahasiswa> = ArrayList()

    while (true) {
        println("\n--- SISTEM DATA MAHASISWA (CONSOLE) ---")
        println("1. Tambah Data")
        println("2. List Data (ArrayList)")
        println("3. Edit Data (Update)")
        println("4. Hapus Data (Delete)")
        println("5. Show Data (Key-Value Map)")
        println("0. Keluar")
        print("Pilih menu: ")

        val pilihan = readlnOrNull()?.trim()

        when (pilihan) {
            "1" -> {
                print("Masukkan NIM: ")
                val nim = readlnOrNull() ?: ""
                print("Masukkan Nama: ")
                val nama = readlnOrNull() ?: ""

                val mhsBaru = Mahasiswa(nim, nama)

                print("Tambah detail info? (y/n): ")
                if (readlnOrNull()?.lowercase() == "y") {
                    print("Masukkan Email: ")
                    val email = readlnOrNull() ?: ""
                    print("Masukkan Asal Kota: ")
                    val kota = readlnOrNull() ?: ""
                    mhsBaru.detail = DetailInfo(email, kota)
                }

                databaseMahasiswa.add(mhsBaru)
                println("Data berhasil ditambahkan!")
            }

            "2" -> {
                val readOnlyList: List<Mahasiswa> = databaseMahasiswa.toList()
                println("\n--- DAFTAR MAHASISWA ---")
                if (readOnlyList.isEmpty()) {
                    println("Data masih kosong.")
                } else {
                    readOnlyList.forEachIndexed { index, it ->
                        val kota = it.detail?.asalKota ?: "Belum diisi"
                        println("${index + 1}. [${it.nim}] ${it.nama} - Asal: $kota")
                    }
                }
            }

            "3" -> {
                print("Masukkan NIM yang akan di-edit: ")
                val nimEdit = readlnOrNull() ?: ""
                val mhs = databaseMahasiswa.find { it.nim == nimEdit }

                if (mhs != null) {
                    print("Nama Baru (sekarang ${mhs.nama}): ")
                    val namaBaru = readlnOrNull()
                    if (!namaBaru.isNullOrBlank()) {
                        mhs.nama = namaBaru // Memicu Custom Setter
                    }
                    println("Data terupdate!")
                } else {
                    println("NIM tidak ditemukan.")
                }
            }

            "4" -> {
                print("Masukkan NIM yang akan dihapus: ")
                val nimHapus = readlnOrNull() ?: ""
                val sukses = databaseMahasiswa.removeIf { it.nim == nimHapus }
                if (sukses) println("Data berhasil dihapus!")
                else println("Data tidak ditemukan!")
            }

            "5" -> {
                println("\n--- DATA DALAM BENTUK MAP (KEY-VALUE) ---")
                val mapMhs: Map<String, Mahasiswa> = databaseMahasiswa.associateBy { it.nim }

                if (mapMhs.isEmpty()) {
                    println("Map kosong.")
                } else {
                    mapMhs.forEach { (key, mhs) ->
                        println("Key (NIM): $key => Value: Mahasiswa(Nama=${mhs.nama})")
                    }
                }
            }

            "0" -> {
                println("Program selesai. Bye!")
                break
            }

            else -> println("Pilihan tidak valid!")
        }
    }
}