<template>
  <el-row>
    <el-col
      class="filter-field-container"
    >
      <div class="filter-field">
        <div class="field-content">

          <div class="field-content-right">
            <el-row style="display:inline-flex;height: 32px;width: 100%;">
              <draggable
                v-model="element.options.attrs.dragItems"
                tag="v-layout"
                group="dimension"
                animation="300"
                :move="onMove"
                class="row wrap justify-space-around theme-drag"
                style="width:calc(100% - 25px);height: 100%;margin:0 10px;border-radius: 4px;overflow-x: auto;display: flex;align-items: center;"
                @end="end2"
              >

                <v-flex
                  v-for="(item,index) in element.options.attrs.dragItems"
                  :key="item.id"
                >
                  <drag-item
                    :key="item.id"
                    :item="item"
                    :index="index"
                    :tool-tip="getTableName(item.tableId)"

                    @closeItem="closeItem"
                  />
                </v-flex>

                <span slot="footer">{{ $t('panel.drag_here') }}</span>
                <i
                  v-show="element.options.attrs.dragItems.length"
                  class="el-icon-error clr-btn"
                  @click="clearItems"
                />
              </draggable>
            </el-row>
          </div>
        </div>
      </div>
    </el-col>
    <el-col
      class="filter-required-container"
    >
      <div class="de-filter-required">
        <el-checkbox
          v-model="element.options.attrs.required"
          @change="requiredChange"
        >{{ $t('commons.required') }}</el-checkbox>
      </div>
    </el-col>
    <el-dialog
      :visible.sync="dialogVisible"
      append-to-body
      :before-close="sureRequired"
    >
    当前组件的展示风格为平铺，如果设为非必填，那么组件的展示风格将切换为下拉展示。
      <div style="text-align: end;margin-top: 16px;">
        <span slot="footer">
          <el-button
            size="mini"
            @click="dialogVisible = false"
          >{{ $t('commons.cancel') }}</el-button>
          <el-button
            type="primary"
            size="mini"
            @click="sureRequired"
          >{{ $t('commons.confirm') }}</el-button>
        </span>
      </div>
    </el-dialog>
  </el-row>
</template>

<script>
import draggable from 'vuedraggable'
import DragItem from '@/components/dragItem'

export default {
  name: 'FilterHead',
  components: {
    draggable,
    DragItem
  },
  props: {
    element: {
      type: Object,
      default: () => {
      }
    }
  },
  data() {
    return {
      dialogVisible: false
    }
  },
  methods: {
    sureRequired() {
      this.element.options.attrs.required = false
      this.dialogVisible = false
    },
    requiredChange(val) {
      if (val === false && (this.element.style.showMode && this.element.style.showMode === 'radio' && !this.element.options.attrs.multiple)) { 
        this.dialogVisible = true
        this.$nextTick(() => {
          this.element.options.attrs.required = true
        })
        return
      }
      this.$emit('required-change', val)
    },
    getTableName(tableId) {
      let tableName = null
      this.$emit('dataset-name', tableId, t => { tableName = t })
      return tableName
    },
    onMove(e, originalEvent) {
      return true
    },
    end2(e) {
    },
    clearItems() {
      this.element.options.attrs.dragItems.splice(0)
      this.element.options.attrs.sort = null
    },
    closeItem(tag) {
      const index = tag.index
      this.element.options.attrs.dragItems.splice(index, 1)
      if (!index) {
        this.element.options.attrs.sort = null
      }
    }
  }
}

</script>
<style lang="scss" scoped>
.filter-field-container {
  width: calc(100% - 70px);
  float: left;
}
.filter-required-container {
  width: 70px;
  float: right;
}
.de-filter-required {
  height: 40px;
  line-height: 40px;
  float: right;
}
.filter-field {
  border-radius: 4px;
  height: 40px;
  overflow-x: overlay;
  .field-content {
    position: relative;
    // display: table;
    width: 100%;
    height: 100%;
    white-space: nowrap;

    .field-content-left {
      width: 50px;
      max-width: 50px;
      position: relative;
      display: table-cell;
      vertical-align: middle;
      margin: 0px;
      padding: 8px;
      height: 100%;
      border-right: none;
      border: 1px solid var(--TableBorderColor, #E6E6E6);;

      .field-content-text {
        box-sizing: border-box;
        overflow: hidden;
        overflow-x: hidden;
        overflow-y: hidden;
        word-break: break-all;
      }
    }

    .field-content-right {
      border-left: none;
      color: #9ea6b2;
      border: 1px solid var(--TableBorderColor, #E6E6E6);
      width: 100%;
      // max-width: 0%;
      position: relative;
      display: inherit;
      vertical-align: middle;
      margin: 0px;
      padding: 4px 0 0 0;
      height: 100%;
      line-height: 100%;
    }
  }

}

.list-group-container:empty, .list-group-container > div:empty {
  display: none;
}

.list-group:empty,
.list-group > div:empty {
  display: inline-block;
  width: 100%;
  height: calc(100% - 13px);
}

.list-group:empty:before,
.list-group > div:empty:before {
  content: attr(data-value);
}

.blackTheme .theme-drag {
  background-color: var(--MainBG, #fff);
}
.clr-btn {
  cursor: pointer;
  position: absolute;
  right: 10px;
  &:hover {
    color: #3370ff;
  }
}
</style>
